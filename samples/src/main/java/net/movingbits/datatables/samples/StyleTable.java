package net.movingbits.datatables.samples;

import net.movingbits.datatables.Datatable;
import net.movingbits.datatables.samples.adapters.SampleTableAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

public class StyleTable extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);

		final Datatable datatable = (Datatable) findViewById(R.id.table);
		datatable.setAdapter(new MyAdapter(this));
	}

	public class MyAdapter extends SampleTableAdapter {

		private final int width;
		private final int height;

		public MyAdapter(final Context context) {
			super(context);

			final Resources resources = context.getResources();

			width = resources.getDimensionPixelSize(R.dimen.table_width);
			height = resources.getDimensionPixelSize(R.dimen.table_height);
		}

		@Override
		public int getRowCount() {
			return 10;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public int getWidth(final int column) {
			return width;
		}

		@Override
		public int getHeight(final int row) {
			return height;
		}

		@Override
		public String getCellString(final int row, final int column) {
			return "Lorem (" + row + ", " + column + ")";
		}

		@Override
		public int getLayoutResource(final int row, final int column) {
			final int layoutResource;
			switch (getItemViewType(row, column)) {
				case 0:
					layoutResource = R.layout.item_table1_header;
				break;
				case 1:
					layoutResource = R.layout.item_table1;
				break;
				default:
					throw new RuntimeException("wtf?");
			}
			return layoutResource;
		}

		@Override
		public int getItemViewType(final int row, final int column) {
			if (row < 0) {
				return 0;
			} else {
				return 1;
			}
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}
	}
}
