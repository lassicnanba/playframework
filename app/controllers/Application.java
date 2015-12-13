package controllers;

import java.util.*;
import models.*;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  // Form用の内部クラス
  public static class SampleForm {
    public String message;
  }
  // ルートにアクセスした際のAction
  public static Result index() {
    List<Message> datas = Message.find.all();
    return ok(index.render("データベースのサンプル",datas));
  }

  // 新規作成フォームのAction
  public static Result add(){
    Form<Message> f = new Form(Message.class);
    return ok(add.render("投稿フォーム",f));
  }

  // /createにアクセスした際のAction
  public static Result create(){
    Form<Message> f = new Form(Message.class).bindFromRequest();
    if (!f.hasErrors()){
      Message data = f.get();
      data.save();
      return redirect("/");
    } else {
      return badRequest(add.render("ERROR",f));
    }
  }

  // /itemにアクセスした際のAction
  public static Result setitem(){
    Form<Message> f = new Form(Message.class);
    return ok(item.render("ID番号を入力。",f));
  }

  // /editにアクセスした際のAction
  public static Result edit(){
    Form<Message> f = new Form(Message.class).bindFromRequest();
    if (!f.hasErrors()){
      Message obj = f.get();
      Long id = obj.id;
      obj = Message.find.byId(id);
      if (obj != null){
        f = new Form(Message.class).fill(obj);
        return ok(edit.render("ID=" + id + "の投稿を編集。",f));
      } else {
        return ok(item.render("ERROR:IDの投稿が見つかりません。",f));
      }
    } else {
      return ok(item.render("ERROR：入力に問題が有ります。",f));
    }
  }

  // /updateにアクセスした際のAction
  public static Result update(){
    Form<Message> f = new Form(Message.class).bindFromRequest();
    if(!f.hasErrors()){
      Message data = f.get();
      data.update();
      return redirect("/");
    } else {
      return ok(edit.render("ERROR：再度入力下さい。",f));
    }
  }


  // // /sendにアクセスした際のAction
  // public static Result send() {
  //   Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
  //   if (!f.hasErrors()){
  //     SampleForm data = f.get();
  //     String msg = "you typed:" + data.message;
  //     return ok(index.render(msg,f));
  //   } else {
  //     return badRequest(index.render("ERROR",form(SampleForm.class)));
  //   }
  // }

  public static Result list(){
    return ok(list.render(

    ));
  }

}
