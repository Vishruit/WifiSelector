package prince.wifiselector;

    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.TextView;

    public class objectReceiver extends Activity {

        TextView tvPerson;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_object_receiver);

            // 1. get passed intent
            Intent intent = getIntent();

            // 2. get person object from intent
            Person person = (Person) intent.getSerializableExtra("person");

            // 3. get reference to person textView
            tvPerson = (TextView) findViewById(R.id.tvPerson);

            // 4. display name & age on textView
            tvPerson.setText(person.toString());

        }
    }
