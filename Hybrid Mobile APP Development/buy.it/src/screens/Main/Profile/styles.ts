import styled from 'styled-components/native';

export const Fieldset = styled.View`
  width: 100%;

  margin-bottom: 25px;
`;

export const AvatarWrapper = styled.View`
  align-items: center;
  justify-content: center;

  margin-bottom: 30px;
`;

export const LogoutButtonWrapper = styled.View`
  margin-top: 16px;

  padding-top: 15px;
  border-top-width: 2px;
  border-top-color: ${({ theme }) => theme.COLORS.GRAY_500};
`;
