package controllers;

import java.util.*;
import models.*;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import play.db.*;

import views.html.*;

public class Application extends Controller {
  // Form用の内部クラス
  public static class SampleForm {
    public String Tasks;
  }
  // ルートにアクセスした際のAction
  public static Result index() {
    List<Tasks> tasks = Tasks.find.all();
    return ok(index.render("データベースのサンプル",tasks));
  }

  // 新規作成フォームのAction
  public static Result add(){
    Form<Tasks> f = new Form(Tasks.class);
    return ok(add.render("投稿フォーム",f));
  }

  // /createにアクセスした際のAction
  public static Result create(){
    Form<Tasks> f = new Form(Tasks.class).bindFromRequest();
    if (!f.hasErrors()){
      Tasks data = f.get();
      data.save();
      return redirect("/");
    } else {
      return badRequest(add.render("ERROR",f));
    }
  }

  // /itemにアクセスした際のAction
  public static Result setitem(){
    Form<Tasks> f = new Form(Tasks.class);
    return ok(item.render("ID番号を入力。",f));
  }

  // /editにアクセスした際のAction
  public static Result edit(){
    Form<Tasks> f = new Form(Tasks.class).bindFromRequest();
    if (!f.hasErrors()){
      Tasks obj = f.get();
      Integer id = obj.id;
      obj = Tasks.find.byId(id);
      if (obj != null){
        f = new Form(Tasks.class).fill(obj);
        return ok(edit.render("ID=" + id + "の投稿を編集。",f));
      } else {
        return ok(item.render("ERROR:IDの投稿が見つかりません。", f));
      }
    } else {
      return ok(item.render("ERROR：入力に問題が有ります。",f));
    }
  }

  // /updateにアクセスした際のAction
  public static Result update(){
    Form<Tasks> f = new Form(Tasks.class).bindFromRequest();
    if(!f.hasErrors()){
      Tasks data = f.get();
      data.update();
      return redirect("/");
    } else {
      return ok(edit.render("ERROR：再度入力下さい。",f));
    }
  }

  // /delにアクセスした際のAction
  public static Result delete(){
    Form<Tasks> f = new Form(Tasks.class);
    return ok(delete.render("削除するID番号",f));
  }

  // /removeにアクセスした際のAction
  public static Result remove(){
    Form<Tasks> f = new Form(Tasks.class).bindFromRequest();
    if (!f.hasErrors()){
      Tasks obj = f.get();
      Integer id = obj.id;
      obj = Tasks.find.byId(id);
      if (obj != null){
        obj.delete();
        return redirect("/");
      } else {
        return ok(delete.render("ERROR：そのID番号は見つかりません。",f));
      }
    } else {
      return ok(delete.render("ERROR：入力にエラーが起こりました。",f));
    }
  }



  public static Result list(){
    return ok(list.render(

    ));
  }

}
