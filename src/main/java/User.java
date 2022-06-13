public class User {
    private String name;
    private String password;

   public static final class UserBuilder{
       private String name;
       private String password;

       public UserBuilder name(String name){
           this.name=name;
           return this;
       }
       public UserBuilder password(String password){
           this.password=password;
           return this;
       }
       public User build(){
           User user = new User();
           if (name==null){
               throw new IllegalStateException("Name cannot be null");
           }else{
               user.name=this.name;
               user.password=this.password;
           }
           return user;
       }

   }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
