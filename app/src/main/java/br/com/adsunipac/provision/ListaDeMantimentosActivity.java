package br.com.adsunipac.provision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.adsunipac.provision.dao.MantimentoDAO;
import br.com.adsunipac.provision.atributos.Mantimento;

public class ListaDeMantimentosActivity extends AppCompatActivity {

    private ListView listaMantimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_mantimentos);

        listaMantimentos = (ListView) findViewById(R.id.lista_mantimentos);

        listaMantimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Mantimento mantimento = (Mantimento) listaMantimentos.getItemAtPosition(position);
                Intent intentVaiProCadastro = new Intent(ListaDeMantimentosActivity.this, CadastroActivity.class);
                intentVaiProCadastro.putExtra("mantimento", mantimento);
                startActivity(intentVaiProCadastro);
            }
        });

        Button novoMantimento = (Button) findViewById(R.id.novo_mantimento);
        novoMantimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProCadastro = new Intent(ListaDeMantimentosActivity.this, CadastroActivity.class);
                startActivity(intentVaiProCadastro);
            }
        });

        registerForContextMenu(listaMantimentos);
    }

    private void carregaLista() {
        MantimentoDAO dao = new MantimentoDAO(this);
        List<Mantimento> mantimentos = dao.buscaMantimentos();
        dao.close();


        ArrayAdapter<Mantimento> adapter = new ArrayAdapter<Mantimento> (this, android.R.layout.simple_list_item_1, mantimentos);
        listaMantimentos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Mantimento mantimento = (Mantimento) listaMantimentos.getItemAtPosition(info.position);

                MantimentoDAO dao = new MantimentoDAO(ListaDeMantimentosActivity.this);
                dao.deleta(mantimento);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

}
