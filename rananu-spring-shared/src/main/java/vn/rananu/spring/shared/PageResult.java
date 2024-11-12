package vn.rananu.spring.shared;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageResult<T> {
    private Integer errorCode;
    private String message;
    private String kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private Integer pageNum;
    private Integer pageSize;
    private List<T> items;
}
