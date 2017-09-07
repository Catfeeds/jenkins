import React from 'react'
import Tool from 'utils/tool'
import { Cells, Cell, CellHeader, CellBody, CellFooter } from 'react-weui'
import wcIconBS64 from 'images/service/wc'
import wcGroupIconBS64 from 'images/service/wc-group'
import qqGroupIconBS64 from 'images/service/qq-group'
import "styles/service/qqgroup.less"

let links = {
	wcService : "http://weixin.qq.com/r/KTttdTPEq27UrXcW9247",
	wcGroup : "http://weixin.qq.com/r/KTttdTPEq27UrXcW9247",
	qqGroup : "http://qm.qq.com/cgi-bin/qm/qr?k=jwEH5aDSdYtQf5tJlG46CoXngvM2FnFV",
}

export default class ServiceConsultApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			wcServiceBS64 : "",
			wcGroupBS64 : "",
			qqGroupBS64 : "",
		};
	}
	componentDidMount(){
		Tool.qrcode()
		.then( QRCode => {
			let options = {
			    format: "png",
			    margin: "2",
			    modulesize : 3
			};
			return {
				wcServiceBS64 :  QRCode.generatePNG( links.wcService, options),
				// wcGroupBS64 :  QRCode.generatePNG(links.wcGroup, options),
				qqGroupBS64 :  QRCode.generatePNG(links.qqGroup, options),
			}
		})
		.then( base64Src => this.setState( Object.assign( {}, this.state, base64Src ) ) )
	}
	render(){
		let {
			wcServiceBS64,
			wcGroupBS64,
			qqGroupBS64,
		} = this.state;
		return (
			<div id="qqgroup_app">
				<Cells>
					<Cell>
						<CellBody>
							<img src={ wcIconBS64 }/>
							<p>公众号（搜索“e飞服务”）</p>
						</CellBody>
						<CellFooter><img src={ wcServiceBS64 }/></CellFooter>
					</Cell>
					{/*<Cell>
						<CellBody>
							<img src={ wcGroupIconBS64 }/>
							<p>微信群</p>
						</CellBody>
						<CellFooter><img src={ wcGroupBS64 }/></CellFooter>
					</Cell>*/}
					<Cell>
						<CellBody>
							<img src={ qqGroupIconBS64 }/>
							<p>QQ群（搜索群号：457733374）</p>
						</CellBody>
						<CellFooter><img src={ qqGroupBS64 }/></CellFooter>
					</Cell>
				</Cells>
			</div>
		)
	}
}