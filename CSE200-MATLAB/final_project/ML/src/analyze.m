function output=analyze(kind,truth,preds)		
% Analyses the accuracy of a prediction
%
% Input:
% kind='acc' classification error
% kind='abs' absolute loss
% 



switch kind
	case 'abs'
		% compute the absolute difference between truth and predictions
		output=sum(abs(truth-preds))/length(truth);
		
	case 'acc' 
        % to be replaced
        output=0;
        
		%% TODO: fill in code here

		  
	
end;

