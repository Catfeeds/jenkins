import React from 'react'
import Tool from 'utils/tool'
import Promise from 'promise'
import { dynamic_type } from 'common/constant'
import { Panel, PanelHeader, PanelBody, PanelFooter, MediaBox, MediaBoxTitle, Cell, CellHeader, CellBody, Label, ButtonArea, Button, Flex, FlexItem } from 'react-weui'
import "styles/dynamic/check-code.less"

const options = {
	    format: "png",
	    margin: "2"
	};

export default class ApplyDynamicApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			code : "",
			qrcodeSrc : "",
			type : 1,
		};
	}
	componentDidMount(){
		let code = this.props.match.params.code;
		
		Promise.all([
			Tool.get( `${ config.ajaxPath }/flyplan/${ code }/qrcode`),
			Tool.qrcode()
		])
		.then( data => {
			let _data = data[0].data;
			// debugger;
			this.setState( Object.assign( {}, this.state, { 
				type : _data.type,
				location : _data.location,
				qrcodeSrc : data[1].generatePNG(`${ config.serverPath}${ config.basePath }/dynamic/detail/${ code }?token=${ _data.token }`, options),
				code,
			} ) )
		})
	}
	render(){
		let {
			code,
			location,
			type,
			qrcodeSrc,
		} = this.state,
		_type = dynamic_type[type];

		return (
			<div id = "dynamic_c_c_app">
				<Panel className = "list_group">
					<PanelHeader><span>{ code }</span></PanelHeader>
					<PanelBody>
						<MediaBox>
							<Cell className = "list_header">
								<CellHeader><Label><span className = { `i ${ _type.style }` }>{ _type.text }</span></Label></CellHeader>
								<CellBody>{ location }</CellBody>
							</Cell>
						</MediaBox>
					</PanelBody>
					<PanelFooter>
						{ qrcodeSrc && <img style = {{ maxHidth: "160px", maxWidth : "160px" }} src= { qrcodeSrc }/> }
					</PanelFooter>
				</Panel>
			</div>
		)
	}
}