package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Follower extends User{
    private boolean subscriptionStatus=false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id",referencedColumnName = "subId")
    private Subscription subscription;
    @OneToMany(mappedBy = "reviewBy", cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Review> review=new ArrayList<>();
    @OneToMany(mappedBy = "reportBy",cascade = CascadeType.DETACH,orphanRemoval = true)
    private List<Report> reports=new ArrayList<>();

}
