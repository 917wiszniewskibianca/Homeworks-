import React , {Component} from "react";
import {render} from "react-dom";
import HomePage from './HomePage';
import FilterTrainsPage from './FilterTrainsPage';
import TrainsPage from './TrainsPage';

export default class App extends Component {
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div>
                {/*<HomePage />*/}
                {/*<TrainsPage />*/}
                {/*<FilterTrainsPage />*/}
                ana
            </div>

        );
    }
}

const appDiv = document.getElementById("app");
render(<App/> , appDiv);
