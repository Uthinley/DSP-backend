package bt.org.dsp.dessungskillingprogram.lib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private Integer status;
    private String text;
    private Object dto;
    private String value;

    // Data for Approval Request
    private String responseText;

    public ResponseMessage(String text) {
        this.text = text;
    }
}
