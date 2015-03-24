/*
 * Copyright (C) 2012 CyberAgent
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eliteinfo.image.effect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage.OnPictureSavedListener;
import jp.co.cyberagent.android.gpuimage.GPUImage3x3ConvolutionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImage3x3TextureSamplingFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageChromaKeyBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDarkenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDifferenceBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDirectionalSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageDivideBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageEmbossFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExclusionBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHardLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLinearBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageLuminosityBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageMultiplyBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageNormalBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageOverlayBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePixelationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePosterizeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageRGBFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSepiaFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import jp.co.cyberagent.android.gpuimage.GPUImageSoftLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSourceOverBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSubtractBlendFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageToneCurveFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.GPUImageVignetteFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageWhiteBalanceFilter;
import jp.co.cyberagent.android.gpuimage.sample.GPUImageFilterTools;
import jp.co.cyberagent.android.gpuimage.sample.GPUImageFilterTools.OnGpuImageFilterChosenListener;
import jp.co.cyberagent.android.gpuimage.sample.R;
import jp.co.cyberagent.android.gpuimage.sample.filter.IF1977Filter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFAmaroFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFBrannanFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFEarlybirdFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFHefeFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFHudsonFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFInkwellFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFLomoFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFLordKelvinFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFNashvilleFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFRiseFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFSierraFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFSutroFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFToasterFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFValenciaFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFWaldenFilter;
import jp.co.cyberagent.android.gpuimage.sample.filter.IFXprollFilter;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ActGallery extends Activity implements OnSeekBarChangeListener,
OnClickListener, OnPictureSavedListener, View.OnTouchListener { 

	private static final int REQUEST_PICK_IMAGE = 1;
	private GPUImageFilter mFilter;
	private FilterAdjuster mFilterAdjuster;
	private GPUImageView mGPUImageView;
	Uri selectedImageUri;
	FilterList filters;
	
//	TextView _view;
	ViewGroup _root;
	private int _xDelta;
	private int _yDelta;
	
	  RelativeLayout ll_main;
	  TextView tvText;
	  
	  EditText etEnterText;
	  Button btnOk;
	  String strText;
	  ImageView ivSaceImage;
	  
	private HorizontalListView hlvImageList;

	 final static float STEP = 200;
	float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 18;
    int i=0;
    String fileName;
    
    
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_gallery);

		((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(this);
		findViewById(R.id.button_choose_filter).setOnClickListener(this);
		findViewById(R.id.button_save).setOnClickListener(this);

		hlvImageList = (HorizontalListView) findViewById(R.id.hlvImageList);


		mGPUImageView = (GPUImageView) findViewById(R.id.gpuimage);

		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, REQUEST_PICK_IMAGE);
		
		btnOk = (Button)findViewById(R.id.btnOk);
		etEnterText = (EditText)findViewById(R.id.etEnterText);
		
		ll_main = (RelativeLayout)findViewById(R.id.ll_main);
	//	tvText = (TextView)findViewById(R.id.tvText);
		_root = (ViewGroup)findViewById(R.id.ll_main);

		 ivSaceImage = (ImageView)findViewById(R.id.ivSaceImage);
		 
		 
		 
		 
		 
    	
		
		btnOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strText = etEnterText.getText().toString();
				if(i==1)
				{
					_root.removeView(tvText);
					
				}
				 
				 
				 
				setTextView();
			}
		});
		

	}



	private void setTextView()
	{	
		
		
		tvText = new TextView(this);
		tvText.setTextSize(mRatio + 18);
		if(strText != null)
		{
			tvText.setText(strText);	
		}
		else
		{
			tvText.setText("Your Text");
		}
		
		    try {
		    	 
		    	 RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				    layoutParams.leftMargin = 50;				    
				    layoutParams.topMargin = 50;
				    layoutParams.bottomMargin = -250;
				    layoutParams.rightMargin = -250;
				    tvText.setLayoutParams(layoutParams);

				    tvText.setOnTouchListener(this);
				   
				   i=1;
				   
				    _root.addView(tvText);
				    
				    
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		   
		

}
private void setupCustomLists() 
{

	//for image list

	filters = new FilterList();

	filters.addFilter("1997", FilterType.I_1977);
	filters.addFilter("Amaro", FilterType.I_AMARO);
	filters.addFilter("Brannan", FilterType.I_BRANNAN);
	filters.addFilter("Earlybird", FilterType.I_EARLYBIRD);
	filters.addFilter("Hefe", FilterType.I_HEFE);
	filters.addFilter("Hudson", FilterType.I_HUDSON);

	filters.addFilter("Inkwell", FilterType.I_INKWELL);
	filters.addFilter("Lomo", FilterType.I_LOMO);
	filters.addFilter("LordKelvin", FilterType.I_LORDKELVIN);
	filters.addFilter("Nashville", FilterType.I_NASHVILLE);
	filters.addFilter("Rise", FilterType.I_NASHVILLE);

	filters.addFilter("Sierra", FilterType.I_SIERRA);
	filters.addFilter("Sutro", FilterType.I_SUTRO);
	filters.addFilter("Toaster", FilterType.I_TOASTER);
	filters.addFilter("Valencia", FilterType.I_VALENCIA);

	filters.addFilter("Walden", FilterType.I_WALDEN);
	filters.addFilter("Xproll", FilterType.I_XPROII);

	filters.addFilter("Contrast", FilterType.CONTRAST);
	//        filters.addFilter("Invert", FilterType.INVERT);
	//        filters.addFilter("Pixelation", FilterType.PIXELATION);
	//        filters.addFilter("Hue", FilterType.HUE);
	//        filters.addFilter("Gamma", FilterType.GAMMA);
	filters.addFilter("Brightness", FilterType.BRIGHTNESS);
	filters.addFilter("Sepia", FilterType.SEPIA);
	//        filters.addFilter("Grayscale", FilterType.GRAYSCALE);
	//        filters.addFilter("Sharpness", FilterType.SHARPEN);
	//        filters.addFilter("Sobel Edge Detection", FilterType.SOBEL_EDGE_DETECTION);
	//        filters.addFilter("3x3 Convolution", FilterType.THREE_X_THREE_CONVOLUTION);
	//        filters.addFilter("Emboss", FilterType.EMBOSS);
	//        filters.addFilter("Posterize", FilterType.POSTERIZE);
	//        filters.addFilter("Grouped filters", FilterType.FILTER_GROUP);
	//        filters.addFilter("Saturation", FilterType.SATURATION);
	//        filters.addFilter("Exposure", FilterType.EXPOSURE);
	//        filters.addFilter("Highlight Shadow", FilterType.HIGHLIGHT_SHADOW);
	//        filters.addFilter("Monochrome", FilterType.MONOCHROME);
	//        filters.addFilter("Opacity", FilterType.OPACITY);
	//        filters.addFilter("RGB", FilterType.RGB);
	//        filters.addFilter("White Balance", FilterType.WHITE_BALANCE);
	filters.addFilter("Vignette", FilterType.VIGNETTE);
	filters.addFilter("ToneCurve", FilterType.TONE_CURVE);
	//
	//        filters.addFilter("Blend (Difference)", FilterType.BLEND_DIFFERENCE);
	//        filters.addFilter("Blend (Source Over)", FilterType.BLEND_SOURCE_OVER);
	//        filters.addFilter("Blend (Color Burn)", FilterType.BLEND_COLOR_BURN);
	//        filters.addFilter("Blend (Color Dodge)", FilterType.BLEND_COLOR_DODGE);
	//        filters.addFilter("Blend (Darken)", FilterType.BLEND_DARKEN);
	//        filters.addFilter("Blend (Dissolve)", FilterType.BLEND_DISSOLVE);
	//        filters.addFilter("Blend (Exclusion)", FilterType.BLEND_EXCLUSION);
	//        filters.addFilter("Blend (Hard Light)", FilterType.BLEND_HARD_LIGHT);
	//        filters.addFilter("Blend (Lighten)", FilterType.BLEND_LIGHTEN);
	//        filters.addFilter("Blend (Add)", FilterType.BLEND_ADD);
	//        filters.addFilter("Blend (Divide)", FilterType.BLEND_DIVIDE);
	//        filters.addFilter("Blend (Multiply)", FilterType.BLEND_MULTIPLY);
	//        filters.addFilter("Blend (Overlay)", FilterType.BLEND_OVERLAY);
	//        filters.addFilter("Blend (Screen)", FilterType.BLEND_SCREEN);
	//        filters.addFilter("Blend (Alpha)", FilterType.BLEND_ALPHA);
	//        filters.addFilter("Blend (Color)", FilterType.BLEND_COLOR);
	//        filters.addFilter("Blend (Hue)", FilterType.BLEND_HUE);
	//        filters.addFilter("Blend (Saturation)", FilterType.BLEND_SATURATION);
	//        filters.addFilter("Blend (Luminosity)", FilterType.BLEND_LUMINOSITY);
	//        filters.addFilter("Blend (Linear Burn)", FilterType.BLEND_LINEAR_BURN);
	//        filters.addFilter("Blend (Soft Light)", FilterType.BLEND_SOFT_LIGHT);
	//        filters.addFilter("Blend (Subtract)", FilterType.BLEND_SUBTRACT);
	//        filters.addFilter("Blend (Chroma Key)", FilterType.BLEND_CHROMA_KEY);
	//        filters.addFilter("Blend (Normal)", FilterType.BLEND_NORMAL);
	//
	filters.addFilter("Lookup (Amatorka)", FilterType.LOOKUP_AMATORKA);




	// Make an array adapter using the built in android layout to render a list of strings
	CustomArrayAdapter adapter = new CustomArrayAdapter(this, filters.names.toArray(new String[filters.names.size()]),selectedImageUri);
	// Assign adapter to HorizontalListView
	hlvImageList.setAdapter(adapter);
	// mHlvCustomListWithDividerAndFadingEdge.setAdapter(adapter);
	hlvImageList.setOnItemClickListener(new OnItemClickListener() 
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			System.out.println("=Position is="+position);

			GPUImageFilter filter= new GPUImageFilter();
			filter = createFilterForType(getApplication(), filters.filters.get(position)); 

			switchFilterTo(filter);
			mGPUImageView.requestRender();

		}
	});
}


@Override
protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
	switch (requestCode) {
	case REQUEST_PICK_IMAGE:
		if (resultCode == RESULT_OK) {
			handleImage(data.getData());
		} else {
			finish();
		}
		break;

	default:
		super.onActivityResult(requestCode, resultCode, data);
		break;
	}
}

@Override
public void onClick(final View v) {
	switch (v.getId()) {
	case R.id.button_choose_filter:
		GPUImageFilterTools.showDialog(this, new OnGpuImageFilterChosenListener() {

			@Override
			public void onGpuImageFilterChosenListener(final GPUImageFilter filter) {
				switchFilterTo(filter);
				mGPUImageView.requestRender();
			}

		});
		break;
	case R.id.button_save:
		saveImage();
		
		
		
		Thread logoTimer = new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					
					 
					 
					sleep(6000);
					
					
					 runOnUiThread(new Runnable() {

	                        @Override
	                        public void run() {
	                          
	                        	System.out.println("==Save image code run---==");
	                        	 ivSaceImage.setVisibility(View.VISIBLE);
	    		            	
	    		            	String strImagePath;
	    		             	
	    		           	 File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);     
	    		                //strImagePath = path+ "/GPUImage" + "/" + "1427114559180.jpg"; //fileName
	    		                strImagePath = path+ "/GPUImage" + "/" + fileName; //fileName
	    		                
	    		              
	    		           	Bitmap imageBitmap ;
	    		           	imageBitmap = BitmapFactory.decodeFile(strImagePath);
	    		           	ivSaceImage.setImageBitmap(imageBitmap);
	    		           	
	                        	try {
									
	                        		getNewImage();
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println("=Inner catch=");
									e.printStackTrace();
								}
	                        	
	                        }
	                    });
					
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		};
		logoTimer.start();
		
		
		
		
		
		
		
		
		/*new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                // your code here
		            	System.out.println("==Save image code run---==");
		            	
		            	
		            	String strImagePath;
		             	
		           	 File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);     
		                strImagePath = path+ "/GPUImage" + "/" + "1427114559180.jpg";
		                
		               ivSaceImage.setVisibility(View.VISIBLE);
		           	Bitmap imageBitmap ;
		           	imageBitmap = BitmapFactory.decodeFile(strImagePath);
		           	ivSaceImage.setImageBitmap(imageBitmap);
		           	
		           	
		         //   getNewImage();
		            }
		        }, 
		        6000 
		);*/
	
		
		break;

	default:
		break;
	}

}

@Override
public void onPictureSaved(final Uri uri) {
	Toast.makeText(this, "Saved: " + uri.toString(), Toast.LENGTH_SHORT).show();
}



private class SaveImage extends AsyncTask<String, String, String>
{
	

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		System.out.println("====doInBackground======");
		saveImage(); 
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		System.out.println("====onPostExecute======");
		getNewImage();
	}
	
}


private void getNewImage() 
{
	 
	
	
	 runOnUiThread(new Runnable() {

         @Override
         public void run() {
        	 
      
	ll_main.setDrawingCacheEnabled(true);
	
	Bitmap bitmap = Bitmap.createBitmap(ll_main.getDrawingCache());
	
	
	File file;
	 String extr = Environment.getExternalStorageDirectory().toString();
     File mFolder = new File(extr + "/Image Editing");

     if (!mFolder.exists()) {
         mFolder.mkdir();
     }

   

     file = new File(mFolder.getAbsolutePath(),fileName);
     
    // String strMyImagePath = file.getAbsolutePath();
     FileOutputStream fos = null;
     try {
         fos = new FileOutputStream(file);
         bitmap.compress(Bitmap.CompressFormat.PNG,70, fos);

         fos.flush();
         fos.close();
        ivSaceImage.setVisibility(View.GONE);
         
     // MediaStore.Images.Media.insertImage(getContentResolver(), fileName, "Screen", "screen"); /// not imp you can remove this line
      
     }catch (FileNotFoundException e) {

         e.printStackTrace();
     } catch (Exception e) {

         e.printStackTrace();
     }

         }
      	 });
}

 private void saveImage() 
{
	
	
	
	  fileName = System.currentTimeMillis() + ".jpg";
  	mGPUImageView.saveToPictures("GPUImage", fileName, this);

  	
  	//getNewImage();
    	    	    	  
}

private void switchFilterTo(final GPUImageFilter filter) {
	if (mFilter == null || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
		mFilter = filter;
		mGPUImageView.setFilter(mFilter);
		mFilterAdjuster = new FilterAdjuster(mFilter);
	}
}

@Override
public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean fromUser) {
	if (mFilterAdjuster != null) {
		mFilterAdjuster.adjust(progress);
	}
	mGPUImageView.requestRender();
}

@Override
public void onStartTrackingTouch(final SeekBar seekBar) {
}

@Override
public void onStopTrackingTouch(final SeekBar seekBar) {
}

private void handleImage(final Uri selectedImage) {
	mGPUImageView.setImage(selectedImage);

	selectedImageUri = selectedImage;

	setupCustomLists();
}







//for image list



private static GPUImageFilter createFilterForType(final Context context, final FilterType type) {
	switch (type) {
	case CONTRAST:
		return new GPUImageContrastFilter(2.0f);
	case GAMMA:
		return new GPUImageGammaFilter(2.0f);
	case INVERT:
		return new GPUImageColorInvertFilter();
	case PIXELATION:
		return new GPUImagePixelationFilter();
	case HUE:
		return new GPUImageHueFilter(90.0f);
	case BRIGHTNESS:
		return new GPUImageBrightnessFilter(1.5f);
	case GRAYSCALE:
		return new GPUImageGrayscaleFilter();
	case SEPIA:
		return new GPUImageSepiaFilter();
	case SHARPEN:
		GPUImageSharpenFilter sharpness = new GPUImageSharpenFilter();
		sharpness.setSharpness(2.0f);
		return sharpness;
	case SOBEL_EDGE_DETECTION:
		return new GPUImageSobelEdgeDetection();
	case THREE_X_THREE_CONVOLUTION:
		GPUImage3x3ConvolutionFilter convolution = new GPUImage3x3ConvolutionFilter();
		convolution.setConvolutionKernel(new float[] {
				-1.0f, 0.0f, 1.0f,
				-2.0f, 0.0f, 2.0f,
				-1.0f, 0.0f, 1.0f
		});
		return convolution;
	case EMBOSS:
		return new GPUImageEmbossFilter();
	case POSTERIZE:
		return new GPUImagePosterizeFilter();
	case FILTER_GROUP:
		List<GPUImageFilter> filters = new LinkedList<GPUImageFilter>();
		filters.add(new GPUImageContrastFilter());
		filters.add(new GPUImageDirectionalSobelEdgeDetectionFilter());
		filters.add(new GPUImageGrayscaleFilter());
		return new GPUImageFilterGroup(filters);
	case SATURATION:
		return new GPUImageSaturationFilter(1.0f);
	case EXPOSURE:
		return new GPUImageExposureFilter(0.0f);
	case HIGHLIGHT_SHADOW:
		return new GPUImageHighlightShadowFilter(0.0f, 1.0f);
	case MONOCHROME:
		return new GPUImageMonochromeFilter(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
	case OPACITY:
		return new GPUImageOpacityFilter(1.0f);  
	case RGB:
		return new GPUImageRGBFilter(1.0f, 1.0f, 1.0f);  
	case WHITE_BALANCE:
		return new GPUImageWhiteBalanceFilter(5000.0f, 0.0f);    
	case VIGNETTE:
		PointF centerPoint = new PointF();
		centerPoint.x = 0.5f;
		centerPoint.y = 0.5f;
		return new GPUImageVignetteFilter(centerPoint, new float[] {0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
	case TONE_CURVE:
		GPUImageToneCurveFilter toneCurveFilter = new GPUImageToneCurveFilter();
		toneCurveFilter.setFromCurveFileInputStream(
				context.getResources().openRawResource(R.raw.tone_cuver_sample));
		return toneCurveFilter;
	case BLEND_DIFFERENCE:
		return createBlendFilter(context, GPUImageDifferenceBlendFilter.class);
	case BLEND_SOURCE_OVER:
		return createBlendFilter(context, GPUImageSourceOverBlendFilter.class);
	case BLEND_COLOR_BURN:
		return createBlendFilter(context, GPUImageColorBurnBlendFilter.class);
	case BLEND_COLOR_DODGE:
		return createBlendFilter(context, GPUImageColorDodgeBlendFilter.class);
	case BLEND_DARKEN:
		return createBlendFilter(context, GPUImageDarkenBlendFilter.class);
	case BLEND_DISSOLVE:
		return createBlendFilter(context, GPUImageDissolveBlendFilter.class);
	case BLEND_EXCLUSION:
		return createBlendFilter(context, GPUImageExclusionBlendFilter.class);


	case BLEND_HARD_LIGHT:
		return createBlendFilter(context, GPUImageHardLightBlendFilter.class);
	case BLEND_LIGHTEN:
		return createBlendFilter(context, GPUImageLightenBlendFilter.class);
	case BLEND_ADD:
		return createBlendFilter(context, GPUImageAddBlendFilter.class);
	case BLEND_DIVIDE:
		return createBlendFilter(context, GPUImageDivideBlendFilter.class);
	case BLEND_MULTIPLY:
		return createBlendFilter(context, GPUImageMultiplyBlendFilter.class);
	case BLEND_OVERLAY:
		return createBlendFilter(context, GPUImageOverlayBlendFilter.class);
	case BLEND_SCREEN:
		return createBlendFilter(context, GPUImageScreenBlendFilter.class);
	case BLEND_ALPHA:
		return createBlendFilter(context, GPUImageAlphaBlendFilter.class);
	case BLEND_COLOR:
		return createBlendFilter(context, GPUImageColorBlendFilter.class);
	case BLEND_HUE:
		return createBlendFilter(context, GPUImageHueBlendFilter.class);
	case BLEND_SATURATION:
		return createBlendFilter(context, GPUImageSaturationBlendFilter.class);
	case BLEND_LUMINOSITY:
		return createBlendFilter(context, GPUImageLuminosityBlendFilter.class);
	case BLEND_LINEAR_BURN:
		return createBlendFilter(context, GPUImageLinearBurnBlendFilter.class);
	case BLEND_SOFT_LIGHT:
		return createBlendFilter(context, GPUImageSoftLightBlendFilter.class);
	case BLEND_SUBTRACT:
		return createBlendFilter(context, GPUImageSubtractBlendFilter.class);
	case BLEND_CHROMA_KEY:
		return createBlendFilter(context, GPUImageChromaKeyBlendFilter.class);
	case BLEND_NORMAL:
		return createBlendFilter(context, GPUImageNormalBlendFilter.class);

	case LOOKUP_AMATORKA:
		GPUImageLookupFilter amatorka = new GPUImageLookupFilter();
		amatorka.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.lookup_amatorka));
		return amatorka;

	case I_1977:
		return new IF1977Filter(context);
	case I_AMARO:
		return new IFAmaroFilter(context);
	case I_BRANNAN:
		return new IFBrannanFilter(context);
	case I_EARLYBIRD:
		return new IFEarlybirdFilter(context);
	case I_HEFE:
		return new IFHefeFilter(context);
	case I_HUDSON:
		return new IFHudsonFilter(context);
	case I_INKWELL:
		return new IFInkwellFilter(context);
	case I_LOMO:
		return new IFLomoFilter(context);
	case I_LORDKELVIN:
		return new IFLordKelvinFilter(context);
	case I_NASHVILLE:
		return new IFNashvilleFilter(context);
	case I_RISE:
		return new IFRiseFilter(context);
	case I_SIERRA:
		return new IFSierraFilter(context);
	case I_SUTRO:
		return new IFSutroFilter(context);
	case I_TOASTER:
		return new IFToasterFilter(context);
	case I_VALENCIA:
		return new IFValenciaFilter(context);
	case I_WALDEN:
		return new IFWaldenFilter(context);
	case I_XPROII:
		return new IFXprollFilter(context);

	default:
		throw new IllegalStateException("No filter of that type!");
	}

}


private static GPUImageFilter createBlendFilter(Context context, Class<? extends GPUImageTwoInputFilter> filterClass) {
	try {
		GPUImageTwoInputFilter filter = filterClass.newInstance();
		filter.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		return filter;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

private enum FilterType {
	CONTRAST, GRAYSCALE, SHARPEN, SEPIA, SOBEL_EDGE_DETECTION, THREE_X_THREE_CONVOLUTION, FILTER_GROUP, EMBOSS, POSTERIZE, GAMMA, BRIGHTNESS, INVERT, HUE, PIXELATION,
	SATURATION, EXPOSURE, HIGHLIGHT_SHADOW, MONOCHROME, OPACITY, RGB, WHITE_BALANCE, VIGNETTE, TONE_CURVE, BLEND_COLOR_BURN, BLEND_COLOR_DODGE, BLEND_DARKEN, BLEND_DIFFERENCE,
	BLEND_DISSOLVE, BLEND_EXCLUSION, BLEND_SOURCE_OVER, BLEND_HARD_LIGHT, BLEND_LIGHTEN, BLEND_ADD, BLEND_DIVIDE, BLEND_MULTIPLY, BLEND_OVERLAY, BLEND_SCREEN, BLEND_ALPHA,
	BLEND_COLOR, BLEND_HUE, BLEND_SATURATION, BLEND_LUMINOSITY, BLEND_LINEAR_BURN, BLEND_SOFT_LIGHT, BLEND_SUBTRACT, BLEND_CHROMA_KEY, BLEND_NORMAL, LOOKUP_AMATORKA,
	I_1977, I_AMARO, I_BRANNAN, I_EARLYBIRD, I_HEFE, I_HUDSON, I_INKWELL, I_LOMO, I_LORDKELVIN, I_NASHVILLE, I_RISE, I_SIERRA, I_SUTRO,
	I_TOASTER, I_VALENCIA, I_WALDEN, I_XPROII
}

public static class FilterList {
	public List<String> names = new LinkedList<String>();
	public List<FilterType> filters = new LinkedList<FilterType>();

	public void addFilter(final String name, final FilterType filter) {
		names.add(name);
		filters.add(filter);
	}
}

public static class FilterAdjuster {
	private final Adjuster<? extends GPUImageFilter> adjuster;

	public FilterAdjuster(final GPUImageFilter filter) {
		if (filter instanceof GPUImageSharpenFilter) {
			adjuster = new SharpnessAdjuster().filter(filter);
		} else if (filter instanceof GPUImageSepiaFilter) {
			adjuster = new SepiaAdjuster().filter(filter);
		} else if (filter instanceof GPUImageContrastFilter) {
			adjuster = new ContrastAdjuster().filter(filter);
		} else if (filter instanceof GPUImageGammaFilter) {
			adjuster = new GammaAdjuster().filter(filter);
		} else if (filter instanceof GPUImageBrightnessFilter) {
			adjuster = new BrightnessAdjuster().filter(filter);
		} else if (filter instanceof GPUImageSobelEdgeDetection) {
			adjuster = new SobelAdjuster().filter(filter);
		} else if (filter instanceof GPUImage3x3TextureSamplingFilter) {
			adjuster = new GPU3x3TextureAdjuster().filter(filter);
		} else if (filter instanceof GPUImageEmbossFilter) {
			adjuster = new EmbossAdjuster().filter(filter);
		} else if (filter instanceof GPUImageHueFilter) {
			adjuster = new HueAdjuster().filter(filter);
		} else if (filter instanceof GPUImagePosterizeFilter) {
			adjuster = new PosterizeAdjuster().filter(filter);
		} else if (filter instanceof GPUImagePixelationFilter) {
			adjuster = new PixelationAdjuster().filter(filter);
		} else if (filter instanceof GPUImageSaturationFilter) {
			adjuster = new SaturationAdjuster().filter(filter);
		} else if (filter instanceof GPUImageExposureFilter) {
			adjuster = new ExposureAdjuster().filter(filter);
		} else if (filter instanceof GPUImageHighlightShadowFilter) {
			adjuster = new HighlightShadowAdjuster().filter(filter);
		} else if (filter instanceof GPUImageMonochromeFilter) {
			adjuster = new MonochromeAdjuster().filter(filter);
		} else if (filter instanceof GPUImageOpacityFilter) {
			adjuster = new OpacityAdjuster().filter(filter);
		} else if (filter instanceof GPUImageRGBFilter) {
			adjuster = new RGBAdjuster().filter(filter);
		} else if (filter instanceof GPUImageWhiteBalanceFilter) {
			adjuster = new WhiteBalanceAdjuster().filter(filter);
		} else if (filter instanceof GPUImageVignetteFilter) {
			adjuster = new VignetteAdjuster().filter(filter);
		} else if (filter instanceof GPUImageDissolveBlendFilter) {
			adjuster = new DissolveBlendAdjuster().filter(filter);
		} else {
			adjuster = null;
		}
	}

	public void adjust(final int percentage) {
		if (adjuster != null) {
			adjuster.adjust(percentage);
		}
	}

	private abstract class Adjuster<T extends GPUImageFilter> {
		private T filter;

		@SuppressWarnings("unchecked")
		public Adjuster<T> filter(final GPUImageFilter filter) {
			this.filter = (T) filter;
			return this;
		}

		public T getFilter() {
			return filter;
		}

		public abstract void adjust(int percentage);

		protected float range(final int percentage, final float start, final float end) {
			return (end - start) * percentage / 100.0f + start;
		}

		protected int range(final int percentage, final int start, final int end) {
			return (end - start) * percentage / 100 + start;
		}
	}

	private class SharpnessAdjuster extends Adjuster<GPUImageSharpenFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setSharpness(range(percentage, -4.0f, 4.0f));
		}
	}

	private class PixelationAdjuster extends Adjuster<GPUImagePixelationFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setPixel(range(percentage, 1.0f, 100.0f));
		}
	}

	private class HueAdjuster extends Adjuster<GPUImageHueFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setHue(range(percentage, 0.0f, 360.0f));
		}
	}

	private class ContrastAdjuster extends Adjuster<GPUImageContrastFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setContrast(range(percentage, 0.0f, 2.0f));
		}
	}

	private class GammaAdjuster extends Adjuster<GPUImageGammaFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setGamma(range(percentage, 0.0f, 3.0f));
		}
	}

	private class BrightnessAdjuster extends Adjuster<GPUImageBrightnessFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setBrightness(range(percentage, -1.0f, 1.0f));
		}
	}

	private class SepiaAdjuster extends Adjuster<GPUImageSepiaFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setIntensity(range(percentage, 0.0f, 2.0f));
		}
	}

	private class SobelAdjuster extends Adjuster<GPUImageSobelEdgeDetection> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setLineSize(range(percentage, 0.0f, 5.0f));
		}
	}

	private class EmbossAdjuster extends Adjuster<GPUImageEmbossFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setIntensity(range(percentage, 0.0f, 4.0f));
		}
	}

	private class PosterizeAdjuster extends Adjuster<GPUImagePosterizeFilter> {
		@Override
		public void adjust(final int percentage) {
			// In theorie to 256, but only first 50 are interesting
			getFilter().setColorLevels(range(percentage, 1, 50));
		}
	}

	private class GPU3x3TextureAdjuster extends Adjuster<GPUImage3x3TextureSamplingFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setLineSize(range(percentage, 0.0f, 5.0f));
		}
	}

	private class SaturationAdjuster extends Adjuster<GPUImageSaturationFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setSaturation(range(percentage, 0.0f, 2.0f));
		}
	}

	private class ExposureAdjuster extends Adjuster<GPUImageExposureFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setExposure(range(percentage, -10.0f, 10.0f));
		}
	}   

	private class HighlightShadowAdjuster extends Adjuster<GPUImageHighlightShadowFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setShadows(range(percentage, 0.0f, 1.0f));
			getFilter().setHighlights(range(percentage, 0.0f, 1.0f));
		}
	}

	private class MonochromeAdjuster extends Adjuster<GPUImageMonochromeFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setIntensity(range(percentage, 0.0f, 1.0f));
			//getFilter().setColor(new float[]{0.6f, 0.45f, 0.3f, 1.0f});
		}
	}

	private class OpacityAdjuster extends Adjuster<GPUImageOpacityFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setOpacity(range(percentage, 0.0f, 1.0f));
		}
	}   

	private class RGBAdjuster extends Adjuster<GPUImageRGBFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setRed(range(percentage, 0.0f, 1.0f));
			//getFilter().setGreen(range(percentage, 0.0f, 1.0f));
			//getFilter().setBlue(range(percentage, 0.0f, 1.0f));
		}
	}   

	private class WhiteBalanceAdjuster extends Adjuster<GPUImageWhiteBalanceFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setTemperature(range(percentage, 2000.0f, 8000.0f));
			//getFilter().setTint(range(percentage, -100.0f, 100.0f));
		}
	}

	private class VignetteAdjuster extends Adjuster<GPUImageVignetteFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setVignetteStart(range(percentage, 0.0f, 1.0f));
		}
	}

	private class DissolveBlendAdjuster extends Adjuster<GPUImageDissolveBlendFilter> {
		@Override
		public void adjust(final int percentage) {
			getFilter().setMix(range(percentage, 0.0f, 1.0f));
		}
	}
}







/** An array adapter that knows how to render views when given CustomData classes */
@SuppressLint("NewApi")
public class CustomArrayAdapter extends BaseAdapter{
	private LayoutInflater mInflater;

	String strEffectName[];
	Uri selectedImageUri;
	Bitmap bitmap;
	public CustomArrayAdapter(Context context, String[] strings,Uri selectedImageUri) {
		// super(context, R.layout.adapter_image_list,filters);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		strEffectName = strings;
		this.selectedImageUri = selectedImageUri;

		try {

			bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectedImageUri);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;

		if (convertView == null) {
			// Inflate the view since it does not exist
			convertView = mInflater.inflate(R.layout.adapter_image_list, parent, false);

			// Create and save off the holder in the tag so we get quick access to inner fields
			// This must be done for performance reasons
			holder = new Holder();
			holder = new Holder(convertView);
			holder.tvEffectName = (TextView) convertView.findViewById(R.id.tvEffectName);
			holder.tvSingleLatter = (TextView)convertView.findViewById(R.id.tvSingleLatter);


			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		// Populate the text
		holder.tvEffectName.setText(""+strEffectName[position]);//.getText());
		holder.tvSingleLatter.setText(""+String.valueOf(strEffectName[position].charAt(0)));        

		// holder.gpuIvEffectImage.setImage(selectedImageUri);



		GPUImageFilter filter= new GPUImageFilter();
		filter = createFilterForType(getApplication(), filters.filters.get(position));		
		holder.gpuIvEffectImage.setFilter(filter);
		holder.gpuIvEffectImage.requestRender();

		return convertView;
	}

	/** View holder for the views we need access to */
	private  class Holder 
	{
		public GPUImageView gpuIvEffectImage;
		public TextView tvEffectName,tvSingleLatter;
		public ImageView ivEffectImage;
		public Holder(View convertView) 
		{
			// TODO Auto-generated constructor stub
			ivEffectImage =  (ImageView) convertView.findViewById(R.id.ivEffectImage);
			ivEffectImage.setImageBitmap(bitmap);
			gpuIvEffectImage =  (GPUImageView) convertView.findViewById(R.id.gpuIvEffectImage);
			// gpuIvEffectImage.setImage(bitmap);*/
		}
		public Holder() {
			// TODO Auto-generated constructor stub
			/* GPUImageView gpuIvEffectImage;
	    	   TextView tvEffectName,tvSingleLatter;
	           ImageView ivEffectImage;*/
		}


	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return strEffectName.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub

		System.out.println("=strEffectName.length;="+strEffectName[position]);
		return 0;
	}
}



public boolean onTouchEvent(MotionEvent event) {
    if (event.getPointerCount() == 2) {
        int action = event.getAction();
        int pureaction = action & MotionEvent.ACTION_MASK;
        if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
            mBaseDist = getDistance(event);
            mBaseRatio = mRatio;
        } else {
            float delta = (getDistance(event) - mBaseDist) / STEP;
            float multi = (float) Math.pow(2, delta);
            mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
            tvText.setTextSize(mRatio + 18);
        }
    }
    return true;
}

int getDistance(MotionEvent event) {
    int dx = (int) (event.getX(0) - event.getX(1));
    int dy = (int) (event.getY(0) - event.getY(1));
    return (int) (Math.sqrt(dx * dx + dy * dy));
}




@Override
public boolean onTouch(View view, MotionEvent event) {
	// TODO Auto-generated method stub
	 final int X = (int) event.getRawX();
	    final int Y = (int) event.getRawY();
	    switch (event.getAction() & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_DOWN:
	            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
	            _xDelta = X - lParams.leftMargin;
	            _yDelta = Y - lParams.topMargin;
	            break;
	        case MotionEvent.ACTION_UP:
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN:
	            break;
	        case MotionEvent.ACTION_POINTER_UP:
	            break;
	        case MotionEvent.ACTION_MOVE:
	            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
	            layoutParams.leftMargin = X - _xDelta;
	            layoutParams.topMargin = Y - _yDelta;
	            layoutParams.rightMargin = -250;
	            layoutParams.bottomMargin = -250;
	            view.setLayoutParams(layoutParams);
	            break;
	    }
	    _root.invalidate();
	    
	    if (event.getPointerCount() == 2) {
	    return false;
	    }
	    else
	    {
	    	return true;
	    }
}



}
