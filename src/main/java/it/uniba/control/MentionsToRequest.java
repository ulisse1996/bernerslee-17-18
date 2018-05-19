package it.uniba.control;

import it.uniba.analysis.MembersAnalysis;
import it.uniba.analysis.MentionsAnalysis;
import it.uniba.util.ControllerUtil;
import it.uniba.util.MentionsPrinter;

public class MentionsToRequest {
	
	static private String needArguments = "Command incomplete , use sna4slack for help";
	static private String memberNotFound = "Member not Found";
	
	boolean showMentionsListTo(final String... command) {
		final ControllerUtil util = new ControllerUtil();
		if (command.length == 3) {
			final MentionsAnalysis request = new MentionsAnalysis();
			if (request.mentionsList(util.getCommand(2,command))) {
				final MembersAnalysis members = new MembersAnalysis();
				members.membersList(util.getCommand(2,command));
				if (members.isInList(util.getCommand(1,command))) {
					request.setMembers(members.getMembers());
					request.setNameFromTo();
					final MentionsPrinter printer = new MentionsPrinter();
					System.out.println(printer.printTo(request.getMentions(),util.getCommand(1,command)));
					return true;
				} else {
					System.out.println(memberNotFound);
				}
			}
		} else if (command.length == 4) {
			final MentionsAnalysis request = new MentionsAnalysis();
			if (request.mentionsListChannel(util.getCommand(2,command),util.getCommand(3,command))) {
				final MembersAnalysis members = new MembersAnalysis();
				members.membersList(util.getCommand(3,command));
				if (members.isInList(util.getCommand(1,command))) {
					request.setMembers(members.getMembers());
					request.setNameFromTo();
					final MentionsPrinter printer = new MentionsPrinter();
					System.out.println(printer.printTo(request.getMentions(),util.getCommand(1,command)));
					return true;
				} else {
					System.out.println(memberNotFound);
				}
			}
		} else {
			System.out.println(needArguments);
		}
		return false;
	}

}