import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

class Verificacoes {

    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // verificar se o nome do usuario eh valido
    protected static boolean verificaUsuario(String usuario){
        if(usuario.matches(".*\\s.*")){
            return false;
        }

        Pattern special = Pattern.compile ("[!@#$%&*()+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(usuario);
        
        if(hasSpecial.find()){
            return false;
        }

        return true;
    }
    
    protected static boolean verificaLocalDate(String data){
        // verificar se a data eh valida
        try {
            LocalDate.parse(data, formatadorData);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
