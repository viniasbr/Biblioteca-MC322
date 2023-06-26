import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class Verificacoes {

    public static DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // verificar se o nome do usuario eh valido
    protected static boolean verificaUsuario(String usuario){
        String usuarioLimpo = usuario.trim().replaceAll("[^0-9[^A-Z][^ ][^a-z]]","");
        if(usuarioLimpo.trim().equals(usuario.trim())) {
            return true;
        }
        return false;
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
