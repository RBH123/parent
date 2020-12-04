package ruan.provider.elasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchingParam {
    private String field;
    private String param;
    private boolean isFuzzy;
}
