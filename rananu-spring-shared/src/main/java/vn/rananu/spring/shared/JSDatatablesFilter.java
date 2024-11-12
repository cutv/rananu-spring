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

    private Integer getPageNumber() {
        return start / pageSize;
    }
}
