package org.ohgiraffers.luluproject.dto;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 5;

    public Pageable getPageable(String... props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    }

    private String link;

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            link = builder.toString();
        }
        return link;
    }

}
