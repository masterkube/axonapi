select distinct  ProcessId  from processstore;

select distinct ProcessName, ActionName from  interactionspacetell where ProcessName = "State" and IsNameInternal = 0;

select distinct ProcessName, ActionName from  interactionspaceask where ProcessName = "State" and IsNameInternal = 0;

select distinct ProcessId as 'from', SignalProcessId as 'to' ,SignalActionName as 'label' from payloadsignalnamestore where  ProcessName != SignalProcessName and  ProcessName ="State";


select ps.ProcessId, GROUP_CONCAT(distinct it.ActionName) as OutBounds, GROUP_CONCAT(distinct ia.ActionName) as InBounds from processstore ps, interactionspacetell it, interactionspaceask ia where it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId
