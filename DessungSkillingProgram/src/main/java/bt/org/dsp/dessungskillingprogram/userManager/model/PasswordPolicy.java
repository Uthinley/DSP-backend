package bt.org.dsp.dessungskillingprogram.userManager.model;

import bt.org.dsp.dessungskillingprogram.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sa_password_policy")
public class PasswordPolicy extends BaseEntity {
    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Integer id;
    private Boolean passLength;
    private Boolean passValidity;
    private Boolean passSpecial;
    private Boolean passEnforce;

}
