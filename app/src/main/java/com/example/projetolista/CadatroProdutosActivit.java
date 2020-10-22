package com.example.projetolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetolista.modelo.Produtos;

public class CadatroProdutosActivit extends AppCompatActivity {
   private final int RESULTADO_NOVO_PRODUTO =10;
   private final int RESULTADO_EDICAO = 11;


   private boolean edicao = false;
   private int id = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatro_produtos);
        setTitle("Cadastro de Produto");
        carregarItem();

    }
    private void carregarItem() {
        Intent intent = getIntent();
        if (intent!=null && intent.getExtras()!=null &&
                intent.getExtras().get("edicaoProd")!=null){
            Produtos produto = (Produtos) intent.getExtras().get("edicaoProd");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextValor = findViewById(R.id.editText_valor);
            editTextNome.setText(produto.getNome());
            editTextValor.setText(String.valueOf(produto.getValor()));
            edicao = true;
            id = produto.getId();
        }

    }

    public void onClickVoltar(View v){
        finish();
    }


    public void onClickSalvar(View v){
        EditText editTextnome =findViewById(R.id.editText_nome);
        EditText editTextvalor = findViewById(R.id.editText_valor);
        String nome = editTextnome.getText().toString();
        Float valor = Float.parseFloat(editTextvalor.getText().toString());
        Produtos produto = new Produtos(nome,valor);

        produto = new Produtos(id, nome, valor);
        Intent intent = new Intent();
        if (edicao){
            intent.putExtra("edicaoProd",produto);
            setResult(RESULTADO_EDICAO,intent);
        }
        else{
        intent.putExtra("NovoProduto",produto);
        setResult(RESULTADO_NOVO_PRODUTO,intent);

        }
        finish();

    }

}


