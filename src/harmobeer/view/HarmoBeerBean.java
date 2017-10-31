package harmobeer.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.harmobeer.mvc.controller.UsuarioController;
import com.harmobeer.vo.Usuario;

@ManagedBean(name = "harmoBeerBean")

@SessionScoped
public class HarmoBeerBean implements Serializable
{

    /**
     *
     */
    private static final long   serialVersionUID = 1L;

    private String              username;
    private String              senha;
    private Usuario             usuarioLogado;
    private UsuarioController   usuarioController;

    // Construtor
    public HarmoBeerBean()
    {
        usuarioLogado = new Usuario();
        usuarioController = new UsuarioController();
    }

    public String entrar()
    {

        usuarioLogado = usuarioController.logar(getUsername(),getSenha());

        if (usuarioLogado != null)
        {
            return "/harmobeer/Main";
        }
        else
        {
            return "/index";
        }

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String pUsername)
    {
        username = pUsername;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String pSenha)
    {
        senha = pSenha;
    }

    /**
     * @return the usuarioLogado
     */
    public Usuario getUsuarioLogado()
    {
        return usuarioLogado;
    }

    /**
     * @param pUsuarioLogado
     *            the usuarioLogado to set
     */
    public void setUsuarioLogado(Usuario pUsuarioLogado)
    {
        usuarioLogado = pUsuarioLogado;
    }

}
