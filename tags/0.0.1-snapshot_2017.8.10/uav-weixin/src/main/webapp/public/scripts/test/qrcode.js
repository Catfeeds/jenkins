import React from 'react'
import Tool from 'utils/tool'

const options = {
	    format: "png",
	    margin: "2"
	}

export default class TestQRCodeApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			qrcodeSrc : "",
		};
	}
	componentDidMount(){
		Tool.qrcode()
		.then( QRCode => {
			let url = "xxxxxxxxxxxxxxxxxxxxxxx";
			this.setState({ qrcodeSrc : QRCode.generatePNG(url, options) })
		})
	}

	render(){
		let {
			qrcodeSrc
		} = this.state;
		return(
			<div id="test_qrcode_app">
				{ !!qrcodeSrc && <img src={ qrcodeSrc }/> }
			</div>
		)
	}
}