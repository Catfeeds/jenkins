import React from 'react'
import Tool from 'utils/tool'

export default class TestQRCodeApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			longitude : "113.30764968",
			latitude : "23.1200491",
		};
	}
	componentDidMount(){
		Tool.loadResource("map")
		.then( () => {
			let {
				longitude,
				latitude,
			} = this.state,
			point = new BMap.Point( longitude, latitude ),
			map = new BMap.Map("test_bdmap_app"),
			marker;
			map.centerAndZoom( point ,15);
			marker = new BMap.Marker( point );
			map.addOverlay(marker);
		})
	}

	render(){
		return(
			<div style = {{ height : "100%" }} id="test_bdmap_app">
			</div>
		)
	}
}