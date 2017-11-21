package br.com.adsunipac.provision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.adsunipac.provision.dao.MantimentoDAO;
import br.com.adsunipac.provision.atributos.Mantimento;

public class CadastroActivity extends AppCompatActivity {

    private CadastroHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        helper = new CadastroHelper(this);

        Intent intent = getIntent();
        Mantimento mantimento = (Mantimento) intent.getSerializableExtra("mantimento");
        if (mantimento != null){
            helper.preencheCadastro(mantimento);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cadastro_ok:
                Mantimento mantimento = helper.pegaMantimento();

                MantimentoDAO dao = new MantimentoDAO(this);
                if (mantimento.getId() != null){
                    dao.atera(mantimento);
                }else{
                    dao.insere(mantimento);
                }
                dao.close();

                Toast.makeText(CadastroActivity.this, "Mantimento " + mantimento.getNome() + " Salvo !", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
