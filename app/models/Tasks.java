package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
public class Tasks extends Model {

  @Id
  public Integer id;

  @Required
  public String task_name;

  @Required
  public String status;

  @CreatedTimestamp
  public Date postdate;

  public static Finder<Integer, Tasks> find = new Finder<Integer, Tasks>(Integer.class, Tasks.class);

  @Override
  public String toString(){
    return("[id:" + id + ", task_name:" + task_name + ", status:" + status + ", date:" + postdate + "]");
  }
}
