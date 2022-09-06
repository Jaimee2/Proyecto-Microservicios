package usuarioservice.usuarioservice.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarioservice.usuarioservice.modelos.Usuario;
import usuarioservice.usuarioservice.servicios.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {

        List<Usuario> listaUsuarios = usuarioService.getAll();

        if (listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaUsuarios);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {

        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.ok(usuario);
    }

}
