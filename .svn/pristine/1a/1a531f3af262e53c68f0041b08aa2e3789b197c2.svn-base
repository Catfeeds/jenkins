import React from 'react'
import Tool from 'utils/tool'
import InputList from 'common/weui/input-list'
import TextArea from 'common/weui/textarea'
import mobileBS64 from 'images/mobile'
import telBS64 from 'images/tel'
import addressBS64 from 'images/address'
import emailBS64 from 'images/email'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody } from 'react-weui'
import "styles/service/contactus.less"

export default class ServiceContactApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			longitude : "113.307951",
			latitude : "23.150724",
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
			map = new BMap.Map("bdmap"),
			marker;
			map.centerAndZoom( point ,18);
			marker = new BMap.Marker( point );
			map.addOverlay(marker);
		})
	}
	render(){
		return (
			<div id="contact_us_app">
				<div id="bdmap" style = {{ height : "60%", minHeight : "300px" }}></div>
				<Panel>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ addressBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								广州市越秀区永福路8号永怡新都605室
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ emailBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								service@efly-atc.com
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ telBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								020-82258167
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
					<PanelBody>
						<MediaBox type = "appmsg">
							<MediaBoxHeader>
								<img src={ mobileBS64 }/>
							</MediaBoxHeader>
							<MediaBoxBody>
								86-18903006535
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
				</Panel>
			</div>
		)
	}
}