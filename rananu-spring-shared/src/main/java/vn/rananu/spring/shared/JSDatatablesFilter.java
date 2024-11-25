package vn.rananu.spring.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class JSDatatablesFilter {
    private Integer draw;
    @JsonProperty("length")
    private Integer pageSize;
    private Integer start;

    public JSDatatablesFilter() {
        this.pageSize = 10;
        this.start = 0;
    }

    public Integer getPageNumber() {
        return start / pageSize;
    }
}
