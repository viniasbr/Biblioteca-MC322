class Verificacoes {

    // verificar se o nome do usuario eh valido
    protected static boolean VerificaUsuario(String usuario){
        if(usuario.matches(".*\\s.*")){
            return false;
        }
        else
            return true;
    }

    
}
