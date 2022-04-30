package bt.org.dsp.dessungskillingprogram.master;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;

public class DeparmentDTO {
    @ExcelRow
    private Integer id;

    @ExcelCell(1)
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
