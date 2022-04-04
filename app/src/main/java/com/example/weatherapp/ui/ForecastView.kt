package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.forecast_view.view.*

@SuppressLint("Recycle")
class ForecastView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    init {
        inflate(context, R.layout.forecast_view, this)

        setMeasuredDimension(57,70)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ForecastView)
        ivTempOfTime.setImageDrawable(attributes.getDrawable(R.styleable.ForecastView_image))
        tvTime.text = attributes.getString(R.styleable.ForecastView_timeText)
        tvTimeTemp.text = attributes.getString(R.styleable.ForecastView_temperatureText)
        attributes.recycle()
    }


}