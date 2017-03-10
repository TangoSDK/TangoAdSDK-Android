package me.tango.android.adsdk.sample;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import me.tango.android.adsdk.Ad;
import me.tango.android.adsdk.AdLoader;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
  private static final String[] TITLES = new String[]{
      "Orange",
      "Apples",
      "Banana",
      "Coconut",
      "Pineapple",
      "Papaya",
      "Mango",
      "Cherry",
      "Black grapes",
      "Peach",
      "Custard apple",
      "Pear"
  };
  private static final String[] DESCRIPTIONS = new String[]{
      "You have enduring patience and willpower. You like to do things slowly; but very thoroughly and are completely undaunted by hard work. You tend to be shy; but are a reliable and trustworthy friend. You have an aesthetic bent of mind. You select your partner with care and thought; you love with all your heart, and are not in for just a fling. You avoid conflict at all costs.",
      "You are extravagant, impulsive and outspoken, often with a bit of a temper. While you may not be the best organizer, you make a good team leader; and are good at taking things forward. You can take quick action in most situations. You enjoy travel immensely. You ooze with charm when you are with your partner. You have an enthusiasm for life, unmatched by most.",
      "You are a softie! Loving, gentle, warm and sympathetic by nature. You often lack in self-confidence and are quite timid by nature. People often take advantage of your sweet temperedness, and sheer vulnerability to a situation. You adore your partner in every which way, both for their mental and physical beauty! Because of the way you are, your relationship is always, very much in harmony!",
      "The coconut lover is a serious, very thoughtful and contemplative person. Though you enjoy socializing, you are particular about the company you keep. You tend to be stubborn but not necessarily foolhardy. Shrewd, quick-witted and alert, you ensure that you are right on top of any given scenario, especially at work. You need a partner with brains, and while passion is important it certainly isn't everything for you.",
      "You are quick to decide and even quicker to act. You are brave in making career changes; if that is what is to your advantage. You have exceptional organizing abilities and are undaunted by the size of the task at hand. You tend to be self reliant, sincere and honest in your dealings with others. Though you are not given to making friends very quickly, but once you do, it is for life. You rarely, if ever, make romantic overtures. Your partner is often impressed with your sterling qualities but disappointed in your ability to show affection.",
      "You are truly fearless and take much that happens in life, in your stride. You give considerable thought to things you do. You have a sense of humor that along with your generous nature keeps you in most people's good books. You are a go-getter in your professional life, and have a knack for being in the right place at the right time. You enjoy meeting new people and seeing new sights whenever you can. Your sense of humor is what attracts members of the opposite sex to you more than anything else. It is simply charming!",
      "A mango lover is a personality to be reckoned with; you often have quite fixed ideas, and influencing you is no easy task. You tend to be an extremist with strong likes and dislikes; and at times even like to control a situation. You enjoy getting involved in something that presents a mental challenge. Strong as you may be, you are like a kitten when you are with your partner. You accommodate the love of your life, and make up for all the strong will elsewhere!",
      "If cherry is your favorite fruit, life isn't always as sweet for you. You often face ups and downs, particularly professionally; and find that you make small sums of money, instead of a lump sum. You have a fertile imagination and are often involved in creative pursuits. You are a very sincere and loyal partner; but find that expressing your feelings is not very easy. Your home is your haven, and you love nothing more than being surrounded by close family and your beloved partner.",
      "You are a polite person in general, but do have quick flare-ups of temper that cool down just as quickly. You enjoy beauty in all forms, including beautiful people. You are very popular because of your warm, gregarious nature. You have a zest for life; you enjoy every thing you do, right from the way you dress, to your style and �lan in your day-to-day life. Your partner must share your zeal and zing for life� to enjoy all you have to offer!",
      "Like a peach, you enjoy the juice of life� in all its lush ripeness! You are the friendly sort, and are quite frank and outspoken, which adds to your charm. You are quick to forgive and forget; and value your friendships highly. You have an independent and ambitious streak in you, that makes you a real go-getter. You are the ideal lover, fiery and passionate but sincere and faithful in love. You don't however like to display all that passion in public.",
      "You are a modest and conservative person; who can be quite sensitive at times. You tend to be thoughtful and contemplative, and therefore are rarely rash in doing things. You are quite ambitious; and are good at anything that requires much detailing or working with numbers. You are quick at finding fault with others. While looking for a partner, you value a person's intellect far above their looks or good old passion. You are quite shy and not very comfortable demonstrating affection.",
      "If you put your mind to something you can do it successfully, but by and large you tend to be fickle and have trouble completing a task with the enthusiasm you started it with. You need to know the results of your efforts almost immediately. You enjoy mental stimulation; and love to get into a good discussion. You tend to be a restless and high-strung person, and are easily excitable. While you are quick to strike up a friendship with someone, maintaining it does not seem to be easy for you."
  };

  private RecyclerView mRecyclerView;
  private final Random random = new Random();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mRecyclerView = (RecyclerView) findViewById(R.id.ads);
    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    final Spinner cid = (Spinner) findViewById(R.id.cid);
    final String[] cids = getResources().getStringArray(R.array.cids);
    final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cids);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    cid.setAdapter(adapter);
    cid.setOnItemSelectedListener(this);
  }

  // AdapterView.OnItemSelectedListener
  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    final String cid = (String) parent.getItemAtPosition(position);
    RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = null;
    if (!TextUtils.isEmpty(cid)) {
      final int items = random.nextInt(TITLES.length + 1) * (random.nextInt(50) + 50);
      adapter = new LocalAdapter(items, new AdLoader(cid));
    }
    mRecyclerView.setAdapter(adapter);
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {
  }

  private final static class LocalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int AD = 0;
    private static final int ITEM = 1;
    final int mLength;
    private LayoutInflater mInflater;
    @NonNull
    private final AdLoader mLoader;

    LocalAdapter(int length, @NonNull AdLoader loader) {
      mLength = length + length / 3 + 1;
      mLoader = loader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      if (mInflater == null) {
        mInflater = LayoutInflater.from(parent.getContext());
      }
      switch (viewType) {
        case AD:
          return new AdViewHolder(mInflater.inflate(R.layout.ad_item, parent, false));
        default:
          return new ItemViewHolder(mInflater.inflate(R.layout.regular_item, parent, false));
      }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      if (holder instanceof ItemViewHolder) {
        ((ItemViewHolder) holder).bind(position);
      } else if (holder instanceof AdViewHolder) {
        ((AdViewHolder) holder).bind(mLoader);
      }
    }

    @Override
    public int getItemViewType(int position) {
      return position % 3 == 0 ? AD : ITEM;
    }

    @Override
    public int getItemCount() {
      return mLength;
    }

    private static final class ItemViewHolder extends RecyclerView.ViewHolder {

      private final TextView mTitle;
      private final TextView mSubtitle;

      ItemViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mSubtitle = (TextView) itemView.findViewById(R.id.subtitle);
      }

      void bind(int position) {
        position -= position / 3;
        mTitle.setText(TITLES[position % TITLES.length]);
        mSubtitle.setText(DESCRIPTIONS[position % TITLES.length]);
      }
    }

    private static final class AdViewHolder extends RecyclerView.ViewHolder implements AdLoader.Listener,
        Ad.LoadBitmapListener,
        Ad.Listener {

      private final View mProgress;
      private final TextView mTitle;
      private final TextView mSubtitle;
      private final ImageView mIcon;
      private final ImageView mImage;
      private final RatingBar mRating;
      private final Button mCta;
      private boolean mLoading;
      private WideImageLoadListener mWideImageLoadListener;
      private String mDefaultCtaText;

      AdViewHolder(View itemView) {
        super(itemView);
        mProgress = itemView.findViewById(R.id.progress);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mSubtitle = (TextView) itemView.findViewById(R.id.subtitle);
        mIcon = (ImageView) itemView.findViewById(R.id.icon);
        mImage = (ImageView) itemView.findViewById(R.id.image);
        mRating = (RatingBar) itemView.findViewById(R.id.rating);
        mCta = (Button) itemView.findViewById(R.id.install);
      }

      void bind(@NonNull AdLoader loader) {
        if (mLoading) {
          return;
        }
        mLoading = true;
        mWideImageLoadListener = new WideImageLoadListener();
        loader.load(itemView.getContext(), this);
      }

      // AdLoader.Listener
      @Override
      public void onSuccess(@NonNull Ad ad) {
        // TODO: Here we have two scenarios
        // TODO: 1) The holder never has been initialized by Ad object yet
        // TODO: 2) The holder already initialized by Ad object
        // TODO: For the first scenario we should just populate the Ad object
        // TODO: For the second scenario we should take into account if current itemView is
        // TODO: visible by user. If it is not we should do the same as for the first scenario
        // TODO: If view is visible then we shouldn't update itemView using incoming Ad object.
        // TODO: Instead we should keep reference to the incoming Ad object and later on
        // TODO: when itemView becomes invisible we should populate previously saved Ad object
        // TODO: Probably it makes sense to expose SingleAdController class from Tango app
        mLoading = false;
        ad.register(itemView, this);
        mProgress.setVisibility(View.GONE);
        mTitle.setText(ad.getTitle());
        mSubtitle.setBackgroundColor(Color.TRANSPARENT);
        mSubtitle.setText(ad.getSubtitle());
        final float rating = ad.getRating();
        if (rating > 0) {
          mRating.setVisibility(View.VISIBLE);
          mRating.setRating(rating);
        } else {
          mRating.setVisibility(View.GONE);
        }
        String ctaText = ad.getCtaText();
        if (TextUtils.isEmpty(ctaText)) {
          if (mDefaultCtaText == null) {
            mDefaultCtaText = itemView.getContext().getString(R.string.open);
          }
          ctaText = mDefaultCtaText;
        }
        mCta.setText(ctaText);
        mCta.setVisibility(View.VISIBLE);
        ad.loadIcon(this);
        ad.loadWideImage(mWideImageLoadListener);
      }

      @Override
      public void onError(@NonNull String errorText) {
        Toast.makeText(itemView.getContext(), errorText, Toast.LENGTH_LONG).show();
        // TODO: try to reload ad one more time. If fails - remove item from adapter
        mLoading = false;
        mProgress.setVisibility(View.GONE);
      }

      // Ad.LoadBitmapListener
      @Override
      public void onLoadBitmapSuccess(@NonNull Bitmap bitmap) {
        mIcon.setImageBitmap(bitmap);
      }

      @Override
      public void onLoadBitmapError(@NonNull String errorText) {
        Toast.makeText(itemView.getContext(), errorText, Toast.LENGTH_LONG).show();
        mIcon.setImageResource(android.R.drawable.stat_sys_warning);
      }

      // Ad.Listener
      @Override
      public void onAdClicked(@NonNull View view) {
      }

      @Override
      public void onAdTracked(@NonNull View view) {
      }

      private final class WideImageLoadListener implements Ad.LoadBitmapListener {

        @Override
        public void onLoadBitmapSuccess(@NonNull Bitmap bitmap) {
          mImage.setVisibility(View.VISIBLE);
          mImage.setImageBitmap(bitmap);
        }

        @Override
        public void onLoadBitmapError(@NonNull String errorText) {
          Toast.makeText(itemView.getContext(), errorText, Toast.LENGTH_LONG).show();
          mImage.setVisibility(View.GONE);
        }
      }
    }
  }
}
