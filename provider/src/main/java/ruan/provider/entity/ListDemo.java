package ruan.provider.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListDemo implements Serializable {

    private static final long serialVersionUID = -1403820854630707481L;
    private LocalDateTime supplyStartDate;
    private LocalDateTime supplyEndDate;
}
