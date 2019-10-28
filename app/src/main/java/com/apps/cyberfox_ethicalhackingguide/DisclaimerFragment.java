package com.apps.cyberfox_ethicalhackingguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class DisclaimerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disclaimer, null);

        CardView htb = view.findViewById(R.id.htb);
        CardView vhub = view.findViewById(R.id.vhub);

        htb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hackthebox.eu/"));
                startActivity(Intent.createChooser(intent,"Open HackTheBox.eu using :"));
            }
        });

        vhub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vulnhub.com/"));
                startActivity(Intent.createChooser(intent,"Open VulnHub using :"));
            }
        });

        return view;
    }
}
