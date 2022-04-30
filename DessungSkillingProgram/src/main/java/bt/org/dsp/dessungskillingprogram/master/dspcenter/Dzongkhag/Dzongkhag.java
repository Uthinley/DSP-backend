package bt.org.dsp.dessungskillingprogram.master.dspcenter.Dzongkhag;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "dzongkhag_master")
public class Dzongkhag {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NotNull
    private String dzongkhag;

}
