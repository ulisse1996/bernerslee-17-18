package it.uniba.control;

import java.util.List;

import it.uniba.analysis.MembersAnalysis;
import it.uniba.analysis.MentionsAnalysis;
import it.uniba.data.Counter;
import it.uniba.util.ControllerUtil;
import it.uniba.util.MentionsPrinter;

class MentionsWeighed {
	
static private String needArguments = "Command incomplete , use sna4slack for help";
	
	boolean showMentionsListWeighted(final String... command) {
		final ControllerUtil util = new ControllerUtil();
		if (command.length == 1) {
			System.out.println(needArguments);
		} else {
			final MentionsAnalysis request = new MentionsAnalysis();
			if (request.mentionsList(util.getCommand(1,command))) {
				final MembersAnalysis members = new MembersAnalysis();
				members.membersList(util.getCommand(1,command));
				request.setMembers(members.getMembers());
				List<Counter> occurence = request.setNameFromTo();
				final MentionsPrinter printer = new MentionsPrinter();
				System.out.println(printer.printWeighedList(request.getMentions(),occurence));
				return true;
		}
		return false;
	}
	
	boolean showMentionsListChannel(final String... command) {
		final ControllerUtil util = new ControllerUtil();
		if (command.length == 3) {
			final MentionsAnalysis request = new MentionsAnalysis();
			if (request.mentionsListChannel(util.getCommand(1,command),util.getCommand(2,command))) {
				final MembersAnalysis members = new MembersAnalysis();
				members.membersList(util.getCommand(2,command));
				request.setMembers(members.getMembers());
				request.setNameFromTo();
				final MentionsPrinter printer = new MentionsPrinter();
				System.out.println(printer.print(request.getMentions()));
				return true;
			}
		} else {
			System.out.println(needArguments);
		}
		return false;
	}


}
