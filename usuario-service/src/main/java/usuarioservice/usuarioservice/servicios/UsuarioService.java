package usuarioservice.usuarioservice.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usuarioservice.usuarioservice.modelos.Coche;
import usuarioservice.usuarioservice.modelos.Usuario;
import usuarioservice.usuarioservice.repositorios.UsuarioRepository;

import java.util.List;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    //Devolvemos una lista de coches que depende del id del usuario
    public List<Coche> getCochesByUsuarioId(int usuarioId){
        //Query al servicio coches
        List<Coche> listaCoches = restTemplate.getForObject("http://localhost:8082/coche/usuario/"+usuarioId,List.class);
        return listaCoches;
    }

    //Devolvemos una lista de motos que depende del id del usuario
    public List<Coche> getMotosByUsuarioId(int usuarioId){
        //Query al servicio coches
        List<Coche> listaMotos = restTemplate.getForObject("http://localhost:8083/motos/usuario/"+usuarioId,List.class);
        return listaMotos;
    }


}
