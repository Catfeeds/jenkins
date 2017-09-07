import React from 'react'
import {withRouter} from "react-router-dom"
let _getHistory;

export function getHistory(){ 
    return _getHistory();
}

class WrapComponent extends React.Component{
    componentWillMount(){
        _getHistory = () => this.props.history
    }
    render(){
        return <div style = {{ overflowY : "auto", height : "100%", WebkitOverflowScrolling : "touch" }}>{this.props.children}</div>
    }
}

export default withRouter( WrapComponent )