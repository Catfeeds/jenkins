import React from 'react'
import IconList from 'common/list/icon-list'
import 'styles/common/weather-list.less'

export default class WeatherList extends React.Component{
	render(){
		let {
			temperature,
			wave,
			weather,
		} = this.props,
		hours = (new Date()).getHours(),
		isDark = ( ( 18 >= hours && 23 <= hours ) || 0 >= hours && 6 <= hours ),
		_temperature,
		_wave,
		_weather,
		text;

		( weather == 1 || weather == 2 ) && ( weather += ( isDark ? "-2" : "-1" ) )

		!!temperature && ( _temperature = (<span key = "_temperature" className = "temperature">{ temperature }</span>) )
		!!wave && ( _wave = (<span key = "_wave" className = "wave">{ wave }M</span>) )
		!!weather && ( _weather = (<span key = "__temperature" className = { `weather tianqi weather${ weather }` }></span>) )

		if( !!_temperature || !!_wave || !!_weather )
			text = (<span className = "__weather">{ [ _weather, _wave, _temperature ] }</span>)
		
		return (
			<IconList { ...this.props } text = { text } />
		)
	}
}