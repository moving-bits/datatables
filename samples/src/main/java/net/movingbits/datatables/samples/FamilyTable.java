package net.movingbits.datatables.samples;

import net.movingbits.datatables.Datatable;
import net.movingbits.datatables.adapters.BaseTableAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FamilyTable extends Activity {

	private class NexusTypes {
		private final String name;
		private final List<Nexus> list;

		NexusTypes(final String name) {
			this.name = name;
			list = new ArrayList<Nexus>();
		}

		public int size() {
			return list.size();
		}

		public Nexus get(final int i) {
			return list.get(i);
		}
	}

	private class Nexus {
		private final String[] data;

		private Nexus(final String name, final String company, final String version, final String api, final String storage, final String inches, final String ram) {
			data = new String[] {
					name,
					company,
					version,
					api,
					storage,
					inches,
					ram };
		}
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);

		final Datatable datatable = (Datatable) findViewById(R.id.table);
		final BaseTableAdapter baseTableAdapter = new FamilyNexusAdapter(this);
		datatable.setAdapter(baseTableAdapter);
	}

	public class FamilyNexusAdapter extends BaseTableAdapter {

		private final NexusTypes familys[];
		private final String headers[] = {
				"Name",
				"Company",
				"Version",
				"API",
				"Storage",
				"Size",
				"RAM",
		};

		private final int[] widths = {
				120,
				100,
				140,
				60,
				70,
				60,
				60,
		};
		private final float density;

		public FamilyNexusAdapter(final Context context) {
			familys = new NexusTypes[] {
					new NexusTypes("Mobiles"),
					new NexusTypes("Tablets"),
					new NexusTypes("Others"),
			};

			density = context.getResources().getDisplayMetrics().density;

			familys[0].list.add(new Nexus("Nexus One", "HTC", "Gingerbread", "10", "512 MB", "3.7\"", "512 MB"));
			familys[0].list.add(new Nexus("Nexus S", "Samsung", "Gingerbread", "10", "16 GB", "4\"", "512 MB"));
			familys[0].list.add(new Nexus("Galaxy Nexus (16 GB)", "Samsung", "Ice cream Sandwich", "15", "16 GB", "4.65\"", "1 GB"));
			familys[0].list.add(new Nexus("Galaxy Nexus (32 GB)", "Samsung", "Ice cream Sandwich", "15", "32 GB", "4.65\"", "1 GB"));
			familys[0].list.add(new Nexus("Nexus 4 (8 GB)", "LG", "Jelly Bean", "17", "8 GB", "4.7\"", "2 GB"));
			familys[0].list.add(new Nexus("Nexus 4 (16 GB)", "LG", "Jelly Bean", "17", "16 GB", "4.7\"", "2 GB"));
			familys[1].list.add(new Nexus("Nexus 7 (16 GB)", "Asus", "Jelly Bean", "16", "16 GB", "7\"", "1 GB"));
			familys[1].list.add(new Nexus("Nexus 7 (32 GB)", "Asus", "Jelly Bean", "16", "32 GB", "7\"", "1 GB"));
			familys[1].list.add(new Nexus("Nexus 10 (16 GB)", "Samsung", "Jelly Bean", "17", "16 GB", "10\"", "2 GB"));
			familys[1].list.add(new Nexus("Nexus 10 (32 GB)", "Samsung", "Jelly Bean", "17", "32 GB", "10\"", "2 GB"));
			familys[2].list.add(new Nexus("Nexus Q", "--", "Honeycomb", "13", "--", "--", "--"));
		}

		@Override
		public int getRowCount() {
			return 14;
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public View getView(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View view;
			switch (getItemViewType(row, column)) {
				case 0:
					view = getFirstHeader(row, column, convertView, parent);
				break;
				case 1:
					view = getHeader(row, column, convertView, parent);
				break;
				case 2:
					view = getFirstBody(row, column, convertView, parent);
				break;
				case 3:
					view = getBody(row, column, convertView, parent);
				break;
				case 4:
					view = getFamilyView(row, column, convertView, parent);
				break;
				default:
					throw new RuntimeException("wtf?");
			}
			return view;
		}

		private View getFirstHeader(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View v = convertView == null ? getLayoutInflater().inflate(R.layout.item_table_header_first, parent, false) : convertView;
			((TextView) v.findViewById(android.R.id.text1)).setText(headers[0]);
			return v;
		}

		private View getHeader(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View v = convertView == null ? getLayoutInflater().inflate(R.layout.item_table_header, parent, false) : convertView;
			((TextView) v.findViewById(android.R.id.text1)).setText(headers[column + 1]);
			return v;
		}

		private View getFirstBody(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View v = convertView == null ? getLayoutInflater().inflate(R.layout.item_table_first, parent, false) : convertView;
			v.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
			((TextView) v.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
			return v;
		}

		private View getBody(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View v = convertView == null ? getLayoutInflater().inflate(R.layout.item_table, parent, false) : convertView;
			v.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
			((TextView) v.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
			return v;
		}

		private View getFamilyView(final int row, final int column, final View convertView, final ViewGroup parent) {
			final View v = convertView == null ? getLayoutInflater().inflate(R.layout.item_table_family, parent, false) : convertView;
			final String string = column == -1 ? getFamily(row).name : "";
			((TextView) v.findViewById(android.R.id.text1)).setText(string);
			return v;
		}

		@Override
		public int getWidth(final int column) {
			return Math.round(widths[column + 1] * density);
		}

		@Override
		public int getHeight(final int row) {
			final int height;
			if (row == -1) {
				height = 35;
			} else if (isFamily(row)) {
				height = 25;
			} else {
				height = 45;
			}
			return Math.round(height * density);
		}

		@Override
		public int getItemViewType(final int row, final int column) {
			final int itemViewType;
			if (row == -1 && column == -1) {
				itemViewType = 0;
			} else if (row == -1) {
				itemViewType = 1;
			} else if (isFamily(row)) {
				itemViewType = 4;
			} else if (column == -1) {
				itemViewType = 2;
			} else {
				itemViewType = 3;
			}
			return itemViewType;
		}

		private boolean isFamily(final int row) {
			int family = 0;
			int r = row;
			while (r > 0) {
				r -= familys[family].size() + 1;
				family++;
			}
			return r == 0;
		}

		private NexusTypes getFamily(final int row) {
			int family = 0;
			int r = row;
			while (r >= 0) {
				r -= familys[family].size() + 1;
				family++;
			}
			return familys[family - 1];
		}

		private Nexus getDevice(final int row) {
			int family = 0;
			int r = row;
			while (r >= 0) {
				r -= familys[family].size() + 1;
				family++;
			}
			family--;
			return familys[family].get(r + familys[family].size());
		}

		@Override
		public int getViewTypeCount() {
			return 5;
		}
	}
}
