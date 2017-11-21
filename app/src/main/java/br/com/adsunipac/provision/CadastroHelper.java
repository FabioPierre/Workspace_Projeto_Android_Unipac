package br.com.adsunipac.provision;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.adsunipac.provision.atributos.Mantimento;

/**
 * Created by TECNOLOGIA on 20/11/2017.
 */

public class CadastroHelper {

    private final EditText campoNome;
    private final EditText campoQuantidade;
    private final EditText campoEstoque;

    private Mantimento mantimento;

    public CadastroHelper(CadastroActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.cadastro_nome);
        campoQuantidade = (EditText) activity.findViewById(R.id.cadastro_quantidade);
        campoEstoque = (EditText) activity.findViewById(R.id.cadastro_estoque);

        mantimento = new Mantimento();
    }

    public Mantimento pegaMantimento() {
        mantimento.setNome(campoNome.getText().toString());
        mantimento.setQuantidade(campoQuantidade.getText().toString());
        mantimento.setEstoque(campoEstoque.getText().toString());

        return mantimento;

    }

    public void preencheCadastro(Mantimento mantimento) {
        campoNome.setText(mantimento.getNome());
        campoQuantidade.setText(mantimento.getQuantidade());
        campoEstoque.setText(mantimento.getEstoque());

        this.mantimento = mantimento;
    }
}
