    package com.example.kaneki.filemanager;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.support.v7.widget.helper.ItemTouchHelper;
    import android.view.ActionMode;
    import android.view.View;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.AbsListView;
    import android.widget.AdapterView;
    import android.widget.GridView;
    import android.widget.ListView;
    import android.widget.TextView;

    import org.w3c.dom.Text;

    public class MainActivity extends AppCompatActivity {

        private GridView gridView;
        private ListView listview;
        private AbstractAdapter gridAdapter;
        private AbstractAdapter listAdapter;
        private ActionMode actionMode = null;
        private AdapterData data;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            data = new AdapterData("system");

            gridView = (GridView)findViewById(R.id.gridView);
            gridAdapter = new GridViewAdapter(this, data);

            listview = (ListView)findViewById(R.id.listView);
            listAdapter = new ListViewAdapter(this, data);

            gridView.setAdapter(gridAdapter);
            listview.setAdapter(listAdapter);

            listview.setVisibility(View.INVISIBLE);

            gridView.setOnItemClickListener(clickListener);
            gridView.setOnItemLongClickListener(longClickListener);
            listview.setOnItemClickListener(clickListener);
            listview.setOnItemLongClickListener(longClickListener);
        }

        private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null){
                    data.setChecked(position, !data.isChecked(position));
                } else {
                    data.openFolder(position);
                    ((TextView)findViewById(R.id.cur_path)).setText(data.getPath());
                }
                gridAdapter.notifyDataSetChanged();
                gridView.setAdapter(gridAdapter);
                listAdapter.notifyDataSetChanged();
                listview.setAdapter(listAdapter);

            }
        };

        private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode == null) {
                    actionMode = startActionMode(actionModeCallback);
                }
                data.setChecked(position, !data.isChecked(position));
                gridAdapter.notifyDataSetChanged();
                gridView.setAdapter(gridAdapter);
                listAdapter.notifyDataSetChanged();
                listview.setAdapter(listAdapter);
                return true;
            }
        };
        @Override
        public void onBackPressed() {
            data.goBack();
            ((TextView)findViewById(R.id.cur_path)).setText(data.getPath());
            gridAdapter.notifyDataSetChanged();
            gridView.setAdapter(gridAdapter);
            listAdapter.notifyDataSetChanged();
            listview.setAdapter(listAdapter);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            if (id == R.id.grid){
                listview.setVisibility(View.INVISIBLE);
                gridView.setVisibility(View.VISIBLE);
            }

            if (id == R.id.list){
                gridView.setVisibility(View.INVISIBLE);
                listview.setVisibility(View.VISIBLE);
            }
            return super.onOptionsItemSelected(item);
        }

        private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.action_mone_menue, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                data.unCheckAll();
                gridAdapter.notifyDataSetChanged();
                gridView.setAdapter(gridAdapter);
                listAdapter.notifyDataSetChanged();
                listview.setAdapter(listAdapter);
            }
        };

    }


