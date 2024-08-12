package vn.rananu.spring.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JSDatatablesResult<T> {
    private Integer start;
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
}
