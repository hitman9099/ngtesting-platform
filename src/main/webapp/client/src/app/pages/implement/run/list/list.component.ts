import {Component, ViewEncapsulation, OnInit, AfterViewInit} from "@angular/core";

import {GlobalState} from "../../../../global.state";
import {CONSTANT} from "../../../../utils/constant";
import {Utils} from "../../../../utils/utils";
import {RouteService} from "../../../../service/route";
import {SlimLoadingBarService} from "../../../../components/ng2-loading-bar";
import {TreeService} from "../../../../components/ng2-tree/src/tree.service";
import {CaseService} from "../../../../service/case";

@Component({
  selector: 'run-list',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./list.scss'],
  templateUrl: './list.html'
})
export class RunList implements OnInit, AfterViewInit {
  models:any[];
  query:any = {keywords: '', status: ''};

  constructor(private _routeService:RouteService, private _state:GlobalState,
              private _caseService:CaseService) {
  }

  ngOnInit() {
    let that = this;
    that.loadData();
  }

  ngAfterViewInit() {
    let that = this;
  }

  create():void {
    let that = this;

    that._routeService.navTo("/pages/implement/run/edit/null");
  }

  delete(eventId:string):void {
    console.log('id=' + eventId);
  }

  loadData() {
    let that = this;
    that._caseService.query(that.query).subscribe((json:any) => {
      that.models = json.data;
    });
  }

}

