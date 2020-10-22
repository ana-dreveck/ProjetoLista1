package com.example.projetolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetolista.modelo.Produtos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listadeProdutos;
    private ArrayAdapter<Produtos> adapterProdutos;
    private int id =0;


    private final int REQUEST_CODE_NOVO_PRODUTO = 1;
    private final int RESULTADO_NOVO_PRODUTO =10;
    private final int REQUEST_EDICAO = 1;
    private final int RESULTADO_EDICAO=11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");

        listadeProdutos = findViewById(R.id.lista_Produtos);
        ArrayList<Produtos> Produtos = new ArrayList<Produtos>();

        adapterProdutos = new ArrayAdapter<Produtos>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                Produtos);
        listadeProdutos.setAdapter(adapterProdutos);
        registerForContextMenu(listadeProdutos);



    }


            private void definirOnClickListenerListView() {
                listadeProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Produtos produtoClick = adapterProdutos.getItem(position);
                        Intent intent = new Intent(MainActivity.this, CadatroProdutosActivit.class);
                        intent.putExtra("edicaoProd", produtoClick);
                        startActivityForResult(intent, REQUEST_EDICAO);
                    }
                });
            }

            private void definirOnClickListenerDelete(){
              listadeProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                  @Override
                  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                      Produtos produtoClick = adapterProdutos.getItem(position);
                      adapterProdutos.remove(produtoClick);
                      adapterProdutos.notifyDataSetChanged();
                      Toast.makeText(MainActivity.this,"Produto Deletado", Toast.LENGTH_LONG).show();

                      return true;
                  }
              });
            }




    public void onClickNovoProduto(View v) {
                Intent intent = new Intent(MainActivity.this, CadatroProdutosActivit.class);
                startActivityForResult(intent, REQUEST_CODE_NOVO_PRODUTO);
            }



    @Override
            public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                if (requestCode == REQUEST_CODE_NOVO_PRODUTO && resultCode == RESULTADO_NOVO_PRODUTO) {
                    Produtos produto = (Produtos) data.getExtras().getSerializable("NovoProduto");
                    produto.setId(++id);
                    this.adapterProdutos.add(produto);
                } else if (requestCode == REQUEST_EDICAO && resultCode == RESULTADO_EDICAO) {
                    Produtos edicaoProd = (Produtos) data.getExtras().getSerializable("edicaoProd");
                    for (int i = 0; i < adapterProdutos.getCount(); i++) {
                        Produtos produto = adapterProdutos.getItem(i);
                        if (produto.getId() == edicaoProd.getId()) {
                            adapterProdutos.remove(produto);
                            adapterProdutos.insert(edicaoProd, i);
                            Toast.makeText(MainActivity.this, "Produto Editado", Toast.LENGTH_LONG).show();
                            break;

                        }
                    }
                }
                super.onActivityResult(requestCode, resultCode, data);
            }





}