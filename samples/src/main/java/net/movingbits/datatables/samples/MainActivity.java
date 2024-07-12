package net.movingbits.datatables.samples;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final B[] b = new B[] {
                new B(getString(R.string.simple_adapter), SimpleTable.class),
                new B(getString(R.string.style_adapter), StyleTable.class),
                new B(getString(R.string.family_adapter), FamilyTable.class),
		};
		setListAdapter(new ArrayAdapter<B>(this, android.R.layout.simple_list_item_1, android.R.id.text1, b));
	}

	@Override
	protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
		startActivity(new Intent(this, ((B) l.getItemAtPosition(position)).class1));
	}

	private static class B {
		private final String string;
		private final Class<? extends Activity> class1;

		B(final String string, final Class<? extends Activity> class1) {
			this.string = string;
			this.class1 = class1;
		}

		@Override
		public String toString() {
			return string;
		}
	}
}
