package example.starter.hbase.admin.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SizeDto implements Serializable {
    private static final long serialVersionUID = 5479349937157298968L;

    private double value;
    private String unit;

    @Override
    public String toString() {
        return value + unit;
    }
}
