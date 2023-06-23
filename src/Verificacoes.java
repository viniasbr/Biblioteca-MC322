import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class Verificacoes {

    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // verificar se o nome do usuario eh valido
    protected static boolean verificaUsuario(String usuario){
        if(usuario.matches(".*\\s.*")){
            return false;
        }
        else
            return true;
    }
    protected static boolean verificaLocalDate(String data)
    {
        try {
            LocalDate.parse(data, formatadorData);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
