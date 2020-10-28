package com.umc.iod.aulaiod.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.umc.iod.aulaiod.R;
import com.umc.iod.aulaiod.model.Usuario;
import com.umc.iod.aulaiod.repository.UsuarioRepository;
import com.umc.iod.aulaiod.util.ThreadManager;

public class TelaCadastroViewModel extends AndroidViewModel {

    private UsuarioRepository usuarioRepository;

    private MutableLiveData<Integer> mensagemErroId = new MutableLiveData<>();
    private MutableLiveData<Usuario> usuarioCadastrado = new MutableLiveData<>();

    public TelaCadastroViewModel(Application application) {
        super(application);
        usuarioRepository = new UsuarioRepository(application);
    }

    public void validarCadastro(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do validarCadastro ");
        ThreadManager.getExecutor().execute(() -> {
            if (usuarioRepository.verificaEmailExistente(usuario.getEmail())) {
                mensagemErroId.postValue(R.string.erro_email_indisponivel);
            } else {
                mensagemErroId.postValue(null);
            }
        });
    }

    public void cadastrar(Usuario usuario) {
        Log.d(getClass().getName(), "Dentro do cadastrar ");
        ThreadManager.getExecutor().execute(() -> {
            Usuario u = usuarioRepository.cadastrar(usuario);
            usuarioCadastrado.postValue(u);
        });
    }

    public MutableLiveData<Integer> getMensagemErroId() {
        return mensagemErroId;
    }

    public MutableLiveData<Usuario> getUsuarioCadastrado() {
        return usuarioCadastrado;
    }
}
