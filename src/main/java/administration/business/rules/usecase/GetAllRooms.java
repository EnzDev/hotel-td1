package administration.business.rules.usecase;

import administration.business.rules.usecase.output.RoomsPresenter;

public interface GetAllRooms {
    void execute(RoomsPresenter presenter);
}
